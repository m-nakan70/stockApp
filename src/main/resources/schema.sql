CREATE TABLE IF NOT EXISTS foodlist(
    id VARCHAR(8)   PRIMARY KEY,
    stock VARCHAR(256),
    qty VARCHAR(8),
    memo VARCHAR(255),
    type VARCHAR(128)
);