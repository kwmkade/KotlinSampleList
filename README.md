# KotlinSampleList（Kotlin学習）

## 環境
* Android Studio Bumblebee | 2021.1.1 Patch 1
* Kotlin
    - 211-1.6.10-release-923-AS7440.40
* minSDK:
    - 26

## 作成手順
### Recycle View + Room Database
1. Empty Activity でプロジェクトを作成
1. ビルドすると「Hello」と表示された画面を確認できる
1. `app/build.gradle` を書き換える
    - `dependencies` に追加
    - `apply plugin: 'kotlin-kapt'` を追加
    - 修正し終えると `Command + option + shift + L` で整形する
1. 書き終えると文法チェック
    - ```bash
./gradlew lint
```
    - [File] > [Sync Project with Gradle Files] を実行  
1. レイアウト関連のxmlファイルを追加
    - `app/src/main/res/layout/activity_main.xml`
    - `app/src/main/res/layout/recyclerview_item.xml`
1. ktファイルを追加
    - `app/src/main/java/com/kwmkade/kotlinsamplelist/MainActivity.kt`
        - メインのアクティビティ
    - `app/src/main/java/com/kwmkade/kotlinsamplelist/{MainViewModel,RecyclerAdapter}.kt`
        - Recycle View のためのクラス
    - `app/src/main/java/com/kwmkade/kotlinsamplelist/db/*kt`
        - Room Database のためのクラス
    - 修正し終えると `Command + option + shift + L` で整形する

### スワイプ削除
1. ゴミ箱アイコン追加
    - `app/src/main/res/drawable` で [New] > [Vector Asset] を選択し `ic_baseline_delete_24.xml` を作成
1. `app/src/main/java/com/kwmkade/kotlinsamplelist/MainActivity.kt` を修正
