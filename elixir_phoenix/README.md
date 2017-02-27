# ElixirPhoenix

# ベンチマーク用サーバ（Elixir / Phoenix）

```bash
$ mix deps.get
```

## 使い方（開発）

```bash
$ mix phoenix.server
```

## 使い方（本番）

```bash
$ MIX_ENV=prod mix compile
$ MIX_ENV=prod mix phoenix.digest
$ MIX_ENV=prod mix phoenix.server

```
