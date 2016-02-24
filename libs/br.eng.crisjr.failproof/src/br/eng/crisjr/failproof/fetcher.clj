(ns br.eng.crisjr.failproof.fetcher
  (:gen-class)
  (:require [clojure.string :as str]))

(def list-beginning "<div class=\"post-content\">")
;; (def list-beginning "<div class=\"body-text\">")
(def list-ending "</div>")

(defn entering-list? [line] (= line list-beginning))
(defn leaving-list? [line] (= (compare line list-ending) 0))

(defn parse-loop
  [lines index limit inside box stuff]
  (if (= index limit)
    stuff
    (let [line (str/trim (nth lines index))]
      (if (not inside)
        ;; should get inside?
        (if (entering-list? line)
          (parse-loop lines (inc index) limit true (str) stuff)
          (parse-loop lines (inc index) limit false nil stuff))
        ;; should leave?
        (if (leaving-list? line)
          (parse-loop lines (inc index) limit (not inside) nil (conj stuff box))
          (parse-loop lines (inc index) limit inside (str box line "\n") stuff))))))

(defn parse
  "Extract the lists from the html file line by line"
  [html]
  (let [lines (str/split-lines html)]
    (parse-loop lines 0 (count lines) false nil (vector))))

(defn fetch
  "Gets the html from the chosen address"
  [source]
  (slurp source))
