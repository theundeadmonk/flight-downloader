(ns flight-downloader.premium-flights.parser-test
  (:require [flight-downloader.premium-flights.parser :as parser]
            [clojure.test :refer :all]))

(def flights
  [{:raw-data {"id" 1
               "title" {"rendered" "Business class Vancouver to Beijing 1980USD"}}
    :parsed-data {:external-id 1
                  :description "Business class Vancouver to Beijing 1980USD"
                  :flight-class "Business"
                  :origin "Vancouver"
                  :destination "Beijing"
                  :amount 1980.0
                  :currency "USD"}}
   {:raw-data {"id" 1
               "title" {"rendered" "Business class Paris to New York 1230€ nonstop"}}
    :parsed-data {:external-id 1
                  :description "Business class Paris to New York 1230€ nonstop"
                  :flight-class "Business"
                  :origin "Paris"
                  :destination "New York"
                  :amount 1230.0
                  :currency "EUR"}}])

(deftest parse-flight
  (testing "it returns a map of parsed flights"
    (doseq [flight flights]
      (is (= (:parsed-data flight) (parser/parse-flight (:raw-data flight))))))

  (testing "it returns an empty map for an invalid flight"
    (let [flight {"id" 1 "title" {"rendered" "American Express: Up to 25000 Bonus Points for Belgium or Luxembourg"}}]
      (is (= {} (parser/parse-flight flight))))))

(deftest parse-flights
  (let [raw-data [{"id" 1 "title" {"rendered" "Business class Vancouver to Beijing 1980USD"}}
                  {"id" 2 "title" {"rendered" "Business class Paris to New York 1230€ nonstop"}}
                  {"id" 3 "title" {"rendered" "American Express: Up to 25000 Bonus Points"}}]]
   (testing "it returns a sequence of valid parsed flights"
     (let [parsed-flights [{:external-id 1
                  :description "Business class Vancouver to Beijing 1980USD"
                  :flight-class "Business"
                  :origin "Vancouver"
                  :destination "Beijing"
                  :amount 1980.0
                  :currency "USD"}
                           {:external-id 2
                  :description "Business class Paris to New York 1230€ nonstop"
                  :flight-class "Business"
                  :origin "Paris"
                  :destination "New York"
                  :amount 1230.0
                  :currency "EUR"}]]
       (is (= parsed-flights (parser/parse-flights raw-data)))))
   (testing "it strips away invalid flights from the result"
     (is (nil? (some #(= 3 (:id %)) (parser/parse-flights raw-data)))))))
