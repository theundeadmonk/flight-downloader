(ns flight-downloader.premium-flights.parser
  (:require [clojure.string :as str]))

(def valid-classes
  ["Business" "First"])

(defn- origin
  [description]
  (re-find (re-matcher #"(?<=class\s).+?(?=\sto)" description)))

(defn- destination
  [description]
  (re-find (re-matcher #"(?<=\sto\s).+?(?=\s\d)" description)))

(defn- amount
  [description]
  (->> description
       (re-matcher #"\d+")
       re-find
       Float/parseFloat))

(defn- parse-currency-code
  [currency]
  (case currency
    "$" "USD"
    "€" "EUR"
    "£" "GBP"
    currency))

(defn- currency
  [description]
  (->> description
       (re-matcher #"(USD|\$|EUR|€|GBP|£|CAD)")
       re-find
       first
       parse-currency-code))

(defn parse-flight
  [flight]
  (let [id (get flight "id")
        description (get-in flight ["title" "rendered"])
        flight-data (str/split description #" ")
        flight-class (first flight-data)]
    (if (some #(= % flight-class) valid-classes)
      {:external-id id
       :description description
       :flight-class flight-class
       :origin (origin description)
       :destination (destination description)
       :amount (amount description)
       :currency (currency description)}
      {})))

(defn parse-flights
  [raw-flights]
  (->> raw-flights
       (map parse-flight)
       (remove empty?)))


