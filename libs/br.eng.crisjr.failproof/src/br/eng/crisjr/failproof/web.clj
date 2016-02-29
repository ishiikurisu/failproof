(ns br.eng.crisjr.failproof.web
  (:gen-class
    :name br.eng.crisjr.failproof.web
    :methods [#^{:static true} [getLists [String] "[Ljava.lang.String;"]
              #^{:static true} [getLinks [String] "[Ljava.lang.String;"]])
  (:require [br.eng.crisjr.failproof.fetcher :as fetcher]
            [br.eng.crisjr.failproof.extractor :as extractor]
            [br.eng.crisjr.failproof.linker :as linker]))

;; MAIN FUNCTIONS
(defn obtain-raw-data
  "Let's get the lists on a web page for you"
  [arg]
  (-> arg fetcher/fetch fetcher/parse))

(defn extract-lists
  "Let's transform these lists into an array of strings"
  [stuff]
  (mapv extractor/extract stuff))

(defn extract-links
  "Let's transform these lists into links"
  [stuff]
  (mapv linker/extract stuff))

;; INTERFACE
(defn -getLists
  [inlet]
  (-> inlet obtain-raw-data extract-lists into-array))

(defn -getLinks
  [inlet]
  (-> inlet obtain-raw-data extract-links into-array))

(defn -main
  "Let's get a web page for you now"
  [& args]
  (-> args (nth 0) obtain-raw-data extract-links println))
