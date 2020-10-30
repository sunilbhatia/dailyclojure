(ns numbers.library.cool-func)

(defn palindrome? [num]
  (def rev (loop [i 1 num-bal num num-build 0]
    (if (= num-bal 0)
        num-build
        (recur 10 (quot num-bal 10) (+ (* num-build i) (mod num-bal 10)))
      )
    ))
  (= rev num)

  )
