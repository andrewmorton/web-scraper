(ns web-scraper.file-writer
  :import (clojure.java.io File))

(defn scraper-directories
  "Creates a vector of directories that should exist for the scraper."
  []
  (let [home #(str (System/getenv "HOME") "/" %1)]
    (apply home ["trulia" "craigslist" "amazon"])))


(defn check-dir-exists?
  [directory]
  (let [directory (File. directory)]
    (if (.exists directory)
      nil
      (.mkdir directory))))

(defn init-dirs
  "Checks whether or not the directories for the scraper already exist."
  [dir-map]
  (let [[first & rest] dir-map]
    (check-dir-exists? first)
    (recur rest)))

(defn writer
  [results file]
  (let [file (File. file)]
    (spit file (str results "\r") :append true)))

(defn trulia-writer
  "Writes information to a trulia.txt file."
  [results]
  (writer results "/scraper_results/trulia.txt"))

(defn write-results
  [input]
  (let [coll input]
    (loop [[first & rest] coll]
      (if-not (empty? rest)
        (do (writer (.attr first "href"))
            (recur rest))
        (println "Finished writing")))))



;;run on compile
;;===============================
(init-dirs scraper-directories)