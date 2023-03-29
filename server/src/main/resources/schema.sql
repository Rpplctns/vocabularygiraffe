CREATE TABLE IF NOT EXISTS words (
    id VARCHAR PRIMARY KEY,
    content VARCHAR NOT NULL,
    last_time_used TIMESTAMP,
    word_type VARCHAR NOT NULL
);