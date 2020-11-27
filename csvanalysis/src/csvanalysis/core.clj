(ns csvanalysis.core
  (:require [clojure-csv.core :as csv])
  (:require [clojure.java.io :as io]))

;(def csv-file (.getFile  (clojure.java.io/resource "verbs.csv")))

(defn process-csv [file-name]
    (with-open  [rdr  (io/reader (.getFile  (clojure.java.io/resource file-name)))]
      (doall (csv/parse-csv rdr))))

(defn -main
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!")


  (process-csv "sample-terms-file.csv")


  )
