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

  ; need to flatten the data object from a list to an array of arrays

  (let [field-index (get-index-of-fields header fields)
        number-of-records (count data)]

      (loop [projected-data [] record 0]
        (println record data (get data record "sunil"))

        (if (> record number-of-records)
          projected-data
          (recur
              (conj projected-data (project-on-record (get data record) field-index))
              (inc record)))
        )
    )


  )

(defn get-csv-contents [file-name]
  (with-open  [rdr  (io/reader (.getFile  (clojure.java.io/resource file-name)))]
    (let [contents (doall (csv/parse-csv rdr))]
      {:header (process-header (first contents)) :data (drop 1 contents)})))
(defn -main
  "I don't do a whole lot."
  [x]

  (let [csv-file (get-csv-contents "sample-terms-file.csv")
        header (:header csv-file)
        data (:data csv-file)]

    (get-data-for-fields header data "date" "spend" "clicks" "customer_search_term")))

