(defproject csvanalysis "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 [org.clojure/clojure "1.10.1"]
                 [clojure-csv/clojure-csv "2.0.1"]
                 ]
  :plugins [[lein-cljfmt "0.7.0"]]
  :main ^:skip-aot csvanalysis.core
  :repl-options {:init-ns csvanalysis.core})
