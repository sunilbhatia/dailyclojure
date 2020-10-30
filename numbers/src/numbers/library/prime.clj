(ns numbers.library.prime)

(defn prime? [n]
  (let [num-range (range 2 (+ 1 (int (Math/sqrt n))))
        divisible (filter #(= (mod n %) 0) num-range)]
    (empty? divisible)))


