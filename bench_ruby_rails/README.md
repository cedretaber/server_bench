# ベンチマーク用サーバ（Ruby on Rails）

## 使い方（開発）

```bash
$ bundle exec rails s
```

## 使い方（本番）

```bash
$ bundle exec rake secret
〜何か長いキー〜
$ SECRET_KEY_BASE=〜何か長いキー〜 RAILS_ENV=production bundle exec rails s
```
