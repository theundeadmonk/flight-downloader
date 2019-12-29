(defproject flight-downloader "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [migratus-lein "0.7.2"]
                 [com.layerware/hugsql "0.5.1"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [org.postgresql/postgresql "9.4-1206-jdbc4"]
                 [org.clojure/data.json "0.2.7"]
                 [clj-http "3.10.0"]
                 [environ "1.1.0"]
                 [lein-environ "1.1.0"]]
  :main ^:skip-aot flight-downloader.core
  :migratus {:store :database
             :migration-dir "migrations"
             :db {:dbtype "postgresql"
                  :dbname (System/getenv "DB_NAME")
                  :host (System/getenv "DB_HOST")
                  :user (System/getenv "DB_USER")
                  :password (System/getenv "DB_PASS")}}
  :plugins [[migratus-lein "0.7.2"]
            [lein-environ "1.1.0"]]
  :target-path "target/%s"
  :profiles
  {:uberjar {:aot :all}
   :dev [:project/dev :profiles/dev]
   :test [:project/test :profiles/test]
   :project/dev {}
   :project/tset {}
   :profiles/dev {}
   :profiles/test {}})
