package com.csis.clientservice.service.impl;

import com.csis.clientservice.common.Result;
import com.csis.clientservice.common.ResultCodeEnum;
import com.csis.clientservice.exception.ResourceNotFoundException;
import com.csis.clientservice.feign.PortfolioOpenFeign;
import com.csis.clientservice.pojo.Client;
import com.csis.clientservice.pojo.ProductDailyProfit;
import com.csis.clientservice.repository.ProductDailyProfitRepository;
import com.csis.clientservice.service.AssetService;
import com.example.portfolio.entity.Product;
import com.example.portfolio.entity.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * 客户资产查询服务实现
 */
@Service
public class AssetServiceImpl implements AssetService {

    /*@Autowired
    private AssetController assetController;*/
    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private PortfolioOpenFeign portfolioOpenFeign;


    private final ProductDailyProfitRepository proRepository;


    public AssetServiceImpl(ProductDailyProfitRepository proRepository) {
        this.proRepository = proRepository;
    }


    @Override
    public List<ProductDailyProfit> getAssetsByClientId(String clientId) {
        List<ProductDailyProfit> assets = proRepository.findByClientId(clientId);
        /*看资产是否为null*/
        if (assets.isEmpty()) {
            throw new ResourceNotFoundException(ResultCodeEnum.NOT_FOUND);
        }
        return assets;
    }

    // 通过产品id查询资产
    @Override
    public ProductDailyProfit getAssetsByProductId(Integer productId,String clientId) {
        ProductDailyProfit assets = proRepository.findByProductId(productId,clientId);
        /*看资产是否为null*/
        if (assets == null) {
            throw new ResourceNotFoundException(ResultCodeEnum.NOT_FOUND);
        }
        return assets;
    }

    @Override
    public Result<ProductDailyProfit> addProductDailyProfit(ProductDailyProfit profit) {
        // 业务校验：同一产品在同一天不允许重复记录
        /*if (proRepository.existsByProductIdAndRecordDate(
                profit.getProductId(),
                profit.getRecordDate() != null ? profit.getRecordDate() : LocalDate.now())
        ) {
            throw new DuplicateProductDailyProfitException("该产品当日记录已存在");
        }*/
        proRepository.insert(profit.getId(),profit.getRecordDate(),profit.getProfitRate(),profit.getProductType(),profit.getProductId(),profit.getPurchaseRatio(),profit.getClientId(),profit.getDailyTotalValue());
        return Result.build(profit,ResultCodeEnum.SUCCESS);
    }

    // 更新客户余额并且更新资产表

    @Override
    public void updateClientBalance(String clientId, String type, BigDecimal amount, String productItemId) {
        Optional<Client> client = clientService.getClientById(clientId);
        BigDecimal totalAssets = client.get().getTotalAssets();
        if (type.equals("赎回")) {
            totalAssets = totalAssets.add(amount);
        } else if (type.equals("购入")) {
            totalAssets = totalAssets.subtract(amount);
        }
        //修改余额
        clientService.updateTotalAssets(clientId, totalAssets);

        //通过组合id得到产品信息并填入资产表
        List<ProductItem> data = portfolioOpenFeign.getByGroupId(productItemId).getData();

        // 检查数据是否为空
        if (data == null || data.isEmpty()) {
            throw new ResourceNotFoundException(ResultCodeEnum.NOT_FOUND);
        }

      // 遍历每个 ProductItem
        for (ProductItem item : data) {
            Integer itemAmount = item.getAmount();
            // 访问字段示例
            Product product = item.getProduct();
            Integer productProductId = product.getProductId();
            BigDecimal floatRate = product.getFloatRate();
            String productType = String.valueOf(product.getProductType());
            /*List<ProductDailyProfit> productDailyProfits = assetController.getAssetsByClientId(clientId).getData();
            if (productDailyProfits == null || productDailyProfits.isEmpty()) {
                throw new ResourceNotFoundException(ResultCodeEnum.NOT_FOUND);
            }*/
            //通过客户id去查询资产表
            ProductDailyProfit productDailyProfits = getAssetsByProductId(productProductId,clientId);
            //判断是否为null
            if (productDailyProfits == null) {
                ProductDailyProfit p1 = new ProductDailyProfit();
                p1.setRecordDate(LocalDate.now());
                p1.setProfitRate(floatRate);
                p1.setProductType(productType);
                p1.setProductId(productProductId);
                p1.setPurchaseRatio(itemAmount);
                p1.setClientId(clientId);
                p1.setDailyTotalValue(BigDecimal.valueOf(1000));
                addProductDailyProfit(p1);
            }else{
                Integer productAccount = 0;
                if (type.equals("赎回")) {
                    productAccount = productDailyProfits.getPurchaseRatio() - itemAmount;
                } else if (type.equals("购入")) {
                    productAccount = productDailyProfits.getPurchaseRatio() + itemAmount;
                }
                proRepository.updateById(productAccount,clientId,productProductId);
            }
        }
    }

    @Override
    public Result<ProductDailyProfit> updateProductDailyProfit(Integer productAccount, String clientId, Integer productProductId) {
        proRepository.updateById(productAccount,clientId,productProductId);
        return Result.build(productAccount,ResultCodeEnum.SUCCESS);
    }
}