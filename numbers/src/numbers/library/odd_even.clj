(ns numbers.library.odd-even)

(defn is-even? [n] (= (mod n 2) 0))

(defn is-odd? [n] (not= (mod n 2) 0))

