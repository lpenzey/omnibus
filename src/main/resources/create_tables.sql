CREATE TABLE IF NOT EXISTS users(
  id         BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(200)   NOT NULL,
  password   VARCHAR(200)   NOT NULL
);
CREATE TABLE IF NOT EXISTS favorites(
  id         BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  userId     BIGINT(20)   NOT NULL,
  route      VARCHAR(200)   NOT NULL,
  stopId     VARCHAR(200)  NOT NULL,
  FOREIGN KEY (userId)
        REFERENCES users(id)
        ON DELETE CASCADE
);


