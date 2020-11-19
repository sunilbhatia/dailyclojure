(ns application.core
  (:gen-class))

(defn print-menu []
  (println "Options are as follows:")
  (println "1. Start the Game")
  (println "2. End the Game")
  (println "Select: "))

(defn get-number-from-cmd-line []
  (Integer/parseInt (read-line)))

(defn evaluate-not-equal [selection entry]

  (cond
    (< entry selection) (println "Your guess is lower")
    (> entry selection) (println "Your guess is higher"))

  false)

(defn evaluate [selection entry]

  (if (= selection entry)
    true
    (evaluate-not-equal selection entry)))

(defn start-game [random-number]

  (println "I have a number between 1 and 100, can you guess it in 10 tries?")

  (loop [tries 0 input-number (get-number-from-cmd-line)]
    (if (evaluate random-number input-number)
      (do (println (str "You guessed it right in " tries " tries")))
      (recur (inc tries) (get-number-from-cmd-line)))))

(defn end-game [] (println "Thank you for playing the guessing game. Please play again later."))
(defn print-usage [] (println "Please choose either option 1 or option 2"))

(defn command-loop []
  (loop [choice (get-number-from-cmd-line)]

    (cond
      (= choice 1) (start-game (rand-int 100))
      (= choice 2) (end-game)
      :else (print-usage))

    (if (= choice 2)
      nil
      (do
        (print-menu)
        (recur (get-number-from-cmd-line))))))

(defn start-application []
  (println "Welcome to the Guessing Number Game...")
  (print-menu)
  (command-loop))