-- :name add-flight! :! :n
-- :doc Add a flight to the database
INSERT INTO premium_flights (
  external_id,
  description,
  flight_class,
  origin,
  destination,
  amount,
  currency,
  created_at,
  updated_at)
VALUES (
  :external-id,
  :description,
  :flight-class,
  :origin,
  :destination,
  :amount,
  :currency,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP)
ON CONFLICT (external_id) DO UPDATE
  SET updated_at = CURRENT_TIMESTAMP

