CREATE TABLE IF NOT EXISTS foodlist(
    id VARCHAR(8)   PRIMARY KEY,
    stock VARCHAR(256),
    memo VARCHAR(256),
    qty VARCHAR(8),
    type VARCHAR(128)
);