(ns csvanalysis.core
  (:require [clojure-csv.core :as csv])
  (:require [clojure.java.io :as io]))

(defn convert-space-to-underscore [header]
  (doall (map #(clojure.string/replace % #" " "_") header)))

(defn remove-special-chars [header]
  (doall (map #(clojure.string/replace % #"[^a-zA-Z0-9 ]" "") header)))

(defn trim-header [header]
  (doall (map #(clojure.string/trim %) header)))

(defn lower-case-header [header]
  (doall (map #(clojure.string/lower-case %) header)))

(defn process-header [header]
  (let [header-no-special-chars (remove-special-chars header)
        header-space-trimmed (trim-header header-no-special-chars)
        header-space-to-underscore (convert-space-to-underscore header-space-trimmed)
        header-processed (lower-case-header header-space-to-underscore)]
    header-processed))

(defn get-index-of-fields [header fields]
  (reduce (fn [ac dt]
            (let [index (.indexOf header dt)]
              (conj ac index))) [] fields))

(defn project-on-record [record field-index]
  (reduce (fn [ac index] (conj ac (get record index))) [] field-index))

(defn get-data-for-fields [header data & fields]

  (let [field-index (get-index-of-fields header fields)
        number-of-records (count data)]

    (loop [projected-data [] record 0]
        ;(println record data (get data record "sunil"))

      (if (> record number-of-records)
        projected-data
        (recur
         (conj projected-data (project-on-record (get data record) field-index))
         (inc record))))))

(defn get-csv-contents [file-name]
  (with-open  [rdr  (io/reader (.getFile  (clojure.java.io/resource file-name)))]
    (let [contents (doall (csv/parse-csv rdr))]
      {:header (process-header (first contents)) :data (drop 1 contents)})))

(defn load-data-file [file-name]

  (let [csv-file (get-csv-contents "sample-terms-file.csv")
        header (:header csv-file)
        data (vec (:data csv-file))]

    (get-data-for-fields header data "date" "spend" "clicks" "customer_search_term")))

(defn get-user-input []
  (read-line))

(defn show-file-load-menu []
  (println "1. Show File Headers")
  (println "2. Select Projection for the loaded file")
  (println "3. Print first X rows")
  (println "4. Count number of rows")
  (println "b  Back to the main menu")
  (println "Enter your choice: "))

(defn show-main-app-menu []

  (println "1. Load a file")
  (println "q  Quit the program")
  (println "Enter your choice: "))


(defn process-users-input-file-load [user-input]

  (cond
    (= "1" user-input) (do (println "Show File Headers...") true)
    (= "2" user-input) (do (println "Select Projection for the loaded file...") true)
    (= "3" user-input) (do (println "Print first X rows...") true)
    (= "4" user-input) (do (println "Count number of rows...") true)
    (= "b" user-input) (do (println "Thank you for using CSV Analyzer. Sad to see you go :(") false)
    :else (do (println "Wrong Choice for File Load...") true)))

(defn process-file-load []

  (loop [continue true]
    (when continue
      (do
        (show-file-load-menu)
        (let [users-input (get-user-input)]
          (recur (process-users-input-file-load users-input))))))

  true)



(defn process-users-input-main-menu [user-input]
  (cond
    (= "1" user-input) (do (process-file-load) true)
    (= "q" user-input) (do (println "Thank you for using CSV Analyzer. Sad to see you go :(") false)
    :else (do (println "Wrong Choice") true)))

(defn -main
  "I don't do a whole lot."
  [x]

  ;(load-data-file "sample-terms-file.csv")

  (loop [continue true]
    (when continue
      (do
        (show-main-app-menu)
        (let [users-input (get-user-input)]
          (recur (process-users-input-main-menu users-input)))))))

