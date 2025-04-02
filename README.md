graph TD
    subgraph 客户端
        WEB[Web浏览器]
        APP[移动端]
    end

    subgraph API网关
        GW[Gateway Service]
    end
    
    subgraph 基础设施层
        DS[Discovery Service]
        CFG[Config Service]
        DB[(MySQL)]
        MQ[(Kafka)]
    end
    
    subgraph 业务服务层
        AP[Approval Service]
        PF[Portfolio Service]
        RSK[Risk Service]
        TRD[Trade Service]
        ADV[Advisory Service]
        USR[User Service]
        AUTH[Auth Service]
    end
    
    WEB --> GW
    APP --> GW
    
    GW --> DS
    GW --> CFG
    
    AP --> DB
    PF --> DB
    PF --> MQ
    RSK --> DB
    TRD --> DB
    
    PF -.-> |Feign| RSK
    TRD -.-> |Feign| AP
    ADV -.-> |Feign| PF
    
    classDef box fill:#f9f9f9,stroke:#333,stroke-width:1px;
    classDef db fill:#e6f3ff,stroke:#4d90fe;
    class WEB,APP,GW,DS,CFG,AP,PF,RSK,TRD,ADV,USR,AUTH box
    class DB,MQ db