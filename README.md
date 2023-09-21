# l-lab-assistant

ロジニキ研サーバーアシスタントBot。

## 環境構築

リポジトリクローン後リポジトリ直下に以下の形式でファイルを作成する。中身はDiscordBotのトークン。
BotTokenの値はbuild時にapp-setting.envファイルにコピーされる。

buildタスクを実行するとbuild/docker下にDockerビルド用の環境が作成される。
以下のコマンドでイメージビルド+実行する。

```text
<projectDir>/build/docker/l-lab-assistant/docker compose up -d --build
```