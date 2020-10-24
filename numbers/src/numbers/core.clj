(ns numbers.core
  (:require  [numbers.library.primary :as primary])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (primary/prime? 23))
  (println (primary/is-even? 10))
  (println (primary/is-odd? 11)))
