(ns br.eng.crisjr.failproof.extractor
  (:gen-class)
  (:require [clojure.string :as string]))

(defn is-title? [line] (->> line (re-find #"<h2.*?>") nil? not))
(defn is-item? [line] (->> line (re-find #"<li>") nil? not))
(defn extract-item [stuff] (re-find #"<li>(.*?)</li>" stuff))
(defn extract-title [stuff] (re-find #"<a.*?>(.*?)</a>" stuff))

;; MAIN FUNCTIONS
(defn extract-filter
  [stuff]
  (or (is-title? stuff) (is-item? stuff)))

(defn extract-map
  [stuff]
  (let [it (if (is-item? stuff)
             (extract-item stuff)
             (extract-title stuff))]
    (nth it 1)))

(defn extract-reduce
  [money]
  (loop [coin (first money)
         change (rest money)
         piggy (str)]
    (if (nil? coin)
      piggy
      (recur (first change) (rest change) (str piggy coin "\n")))))

(defn extract
  "Turns a string of raw html data into a LF-separated string.
  The first line is the list title; while the rest is the list's content"
  [stuff]
  (->> stuff
       string/split-lines
       (filter extract-filter)
       (mapv extract-map)
       extract-reduce))
