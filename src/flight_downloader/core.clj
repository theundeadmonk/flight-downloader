(ns flight-downloader.core
  (:require [flight-downloader.premium-flights.core :as premium-flights])
  (:gen-class))

(defn -main
  [& args]
  (premium-flights/add-flights))
