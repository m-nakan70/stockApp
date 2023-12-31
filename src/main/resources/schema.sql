CREATE TABLE IF NOT EXISTS foodlist(
    id VARCHAR(8)   PRIMARY KEY,
    stock VARCHAR(256),
    memo VARCHAR(256),
    qty VARCHAR(8),
    type VARCHAR(128),
    subtype VARCHAR(128),
    uby VARCHAR(8),
    times TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    datet DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS dailylist(
    id VARCHAR(8)   PRIMARY KEY,
    daily VARCHAR(256),
    memo VARCHAR(256),
    qty VARCHAR(8),
    type VARCHAR(128),
    times TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    datet DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS emglist(
    id VARCHAR(8)   PRIMARY KEY,
    stock VARCHAR(256),
    memo VARCHAR(256),
    qty VARCHAR(8),
    type VARCHAR(128),
    exp VARCHAR(256),
    times TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    datet DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);