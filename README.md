# sts-tera5-mybatis3-todo
terasoluna5に則って、todoアプリケーションの作成

## 環境
apache-maven-3.6.3
Amazon Corretto 8

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



## おすすめ設定
- パッケージプレゼンテーション
階層(Hierarchical)に設定
- git同期化
ウィンドウ > パースペクティブ > パースペクティブを開く
- .gitignore作成
```
target/
```

