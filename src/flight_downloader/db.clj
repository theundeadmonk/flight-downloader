(ns flight-downloader.db
  (:require [clojure.java.jdbc :as j]
            [environ.core :refer [env]]))

(def db
  {:dbtype "postgresql"
   :dbname (env :db-name)
   :host (env :db-host)
   :user (env :db-user)
   :password (env :db-pass)})
