# ベンチマーク用サーバ（Scala / Play Framework）

## 使い方（開発）

```bash
$ sbt run
```

## 使い方（本番）

```bash
$ sbt clean stage
$ target/universal/stage/bin/scala_play -Dplay.crypto.secret=abcdefghijk
```
