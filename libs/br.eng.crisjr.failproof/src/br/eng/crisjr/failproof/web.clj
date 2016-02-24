(ns br.eng.crisjr.failproof.web
  (:gen-class
    :name br.eng.crisjr.failproof.web
    :methods [#^{:static true} [process [String] "[Ljava.lang.String;"]])
  (:require [br.eng.crisjr.failproof.fetcher :as fetcher]
            [br.eng.crisjr.failproof.extractor :as extractor]))

(defn obtain-raw-data
  "Let's get the lists on a web page for you"
  [arg]
  (-> arg fetcher/fetch fetcher/parse))

(defn extract-data
  "Let's transform these lists into an array of strings"
  [stuff]
  (mapv extractor/extract stuff))

(defn -process
  [inlet]
  (-> inlet obtain-raw-data extract-data into-array))

(defn -main
  "Let's get a web page for you now"
  [& args]
  (-> args (nth 0) obtain-raw-data extract-data into-array println))
