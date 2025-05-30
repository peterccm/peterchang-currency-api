CREATE TABLE IF NOT EXISTS CURRENCY (
    CURRENCYID VARCHAR(3) PRIMARY KEY,     -- 幣別代碼
    CURRENCYNAME VARCHAR(100),             -- 幣別名稱
    CURRENCYENAME VARCHAR(100),            -- 幣別英文名稱
    SYMBOL VARCHAR(20),                    -- 幣別符號
    RATE DECIMAL(18, 6),                   -- 匯率
    RATEFLOAT DECIMAL(18, 6),              -- 完整匯率
    CREATETIME TIMESTAMP,                  -- 建立時間
    UPDATETIME TIMESTAMP                   -- 更新時間
);