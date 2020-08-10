# sts-tera5-mybatis3-todo
terasoluna5に則って、todoアプリケーションの作成
> [参照] http://terasolunaorg.github.io/guideline/5.6.0.RELEASE/ja/Tutorial/TutorialTodo.html

## 環境
apache-maven-3.6.3
Amazon Corretto 8
tomcat-9.0

## 事前準備
- stsインストール
> https://techfun.cc/spring/windows-spring-install.html
- 日本語化
> https://qiita.com/kxn4t/items/2f86c57956fa052d9236


- stsインストール
## プロジェクトの作成編
- MyBatis3用のブランクプロジェクトの作成 (DgroupIdとDartifactIdは自分で決めてね)
```
mvn archetype:generate -B^
 -DarchetypeGroupId=org.terasoluna.gfw.blank^
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-mybatis3-archetype^
 -DarchetypeVersion=5.6.0.RELEASE^
 -DgroupId=com.example.todo^
 -DartifactId=sts-tera5-mybatis3-todo^
 -Dversion=1.0.0-SNAPSHOT
```
# Todo編
## mybatis3設定
- postgresql設定(/sts-tera5-mybatis3-todo/src/main/resources/META-INF/spring/sts-tera5-mybatis3-todo-infra.properties)
```
database=POSTGRESQL
database.url=jdbc:postgresql://127.0.0.1:5432/postgres
database.username=postgres
database.password=postgres
database.driverClassName=org.postgresql.Driver
# connection pool
cp.maxActive=96
cp.maxIdle=16
cp.minIdle=0
cp.maxWait=60000
```
- pom.xmlの設定でpostgresqlのコメントアウトを削除


## おすすめ設定
- パッケージプレゼンテーション
階層(Hierarchical)に設定
- git同期化
ウィンドウ > パースペクティブ > パースペクティブを開く
- .gitignore作成
```
target/
```
- フォーマッター
設定 > Ant エディタ フォーマッター のスペースの代わりにタブ文字を使用のチェックを外す
長い要素タグの折り返しにチェックを入れる


# Rest編
- DHC REST Clientを導入
Chromeの「Tools」→「拡張機能(Extensions)」
> https://chrome.google.com/webstore/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm?hl=ja
