(ns flight-downloader.premium-flights.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [flight-downloader.db :refer [db]]
            [flight-downloader.hugsql :as hugsql]
            [flight-downloader.premium-flights.parser :as parser]))

(def flights-url
  "https://www.premium-flights.com/wp-json/wp/v2/posts")

(def flights-data
  (-> flights-url
      client/get
      :body
      json/read-str))

(def flights
  (parser/parse-flights flights-data))

(defn add-flights []
  (doseq [flight flights]
    (hugsql/add-flight! db flight)))
