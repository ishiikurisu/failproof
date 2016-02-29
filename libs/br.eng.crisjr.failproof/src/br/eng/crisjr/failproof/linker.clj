(ns br.eng.crisjr.failproof.linker
  (:gen-class)
  (:require [clojure.string :as string]
            [br.eng.crisjr.failproof.extractor :as extractor]))

(defn extract-link [stuff] (re-find #"href=\"(.*?)\"" stuff))

;; MAIN FUNCTIONS
(defn extract-filter
  [inlet]
  (extractor/is-title? inlet))

(defn extract-pack
  [stuff]
  (let [inlet (first stuff)]
    (str (nth (extractor/extract-title inlet) 1) "\n"
         (nth (extract-link inlet) 1))))

(defn extract
  "Turns a string of raw html data into "
  [stuff]
  (->> stuff
       string/split-lines
       (filter extract-filter)
       extract-pack))
