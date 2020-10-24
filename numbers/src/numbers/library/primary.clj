(ns numbers.library.primary)

(defn prime? [n]

  (let [num-range (range 2 (+ 1 (int (Math/sqrt n))))
        divisible (filter #(= (mod n %) 0) num-range)
        ]

    (empty? divisible)
  )
  )
  ;(loop [n n]
  ;  (println n)
  ;  (if (= n 0)
  ;    false
  ;    (recur (dec n)))))

(defn is-even? [n] (= (mod n 2) 0))

(defn is-odd? [n] (not= (mod n 2) 0))
