(ns application.core
  (:gen-class))

(defn print-menu []
  (println "Options are as follows:")
  (println "1. Start the Game")
  (println "2. End the Game")
  (println "Select: "))

(defn start-game []

  (def random-number (rand-int 100))
  (def flag (atom true))
  (def input-number (atom 0))
  (def tries (atom 0))

  (println "I have a number between 1 and 100, can you guess it in 10 tries?")

  (while @flag
    (do

      (when (= @tries 10)
        (println (str "You failed to guess in 10 tries. I had thought of the number " random-number))
        (reset! flag false))

      (swap! tries inc)

      ;(println random-number)

      (reset! input-number (->> (read-line) (Integer/parseInt)))

      (if (= @input-number random-number)

        (do
          (println (str "You guessed it right in " @tries " tries"))
          (reset! flag false))

        (do
          (if (< @input-number random-number)
            (println "Your guess is lower")
            (println "Your guess is higher")))))))

(defn end-game [] (println "Thank you for playing the guessing game. Please play again later."))
(defn print-usage [] (println "Please choose either option 1 or option 2"))

(defn command-loop []
  (loop [choice (read-line)]

    (def choice-number (Integer/parseInt choice))

    (cond
      (= choice-number 1) (start-game)
      (= choice-number 2) (end-game)
      :else (print-usage))

    (if (= choice-number 2)
      nil
      (do
        (print-menu)
        (recur (read-line))))))

(defn start-application []

  (println "Welcome to the Guessing Number Game...")
  (print-menu)
  (command-loop))

(defn test-sunil []

  (let [c (->> (read-line)
               (Integer/parseInt))]
    ;(println (+ c 100))

    (cond
      (= c 1) (println "One")
      (= c 2) (println "Two")
      :else "W  rong Option")))



