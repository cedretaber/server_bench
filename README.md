# server bench

何種類化の言語／フレームワークでベンチマークを取って比較したい。

## DB Migration

DBを全てのサーバアプリケーションで共用するので、フレームワークのマイグレーションは使わない。

```sql
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `dept` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4;
```
