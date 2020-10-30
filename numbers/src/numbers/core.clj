(ns numbers.core
  (:require [numbers.library.prime :as prime]
            [numbers.library.odd-even :as odd-even]
            [numbers.library.cool-func :as cool-func])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (prime/prime? 23))
  (println (odd-even/is-even? 10))
  (println (cool-func/palindrome? 1234))
  (println (cool-func/palindrome? 1221))
  (println (odd-even/is-odd? 11)))
