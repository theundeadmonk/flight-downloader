(ns flight-downloader.hugsql
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "queries/premium_flights.sql")
