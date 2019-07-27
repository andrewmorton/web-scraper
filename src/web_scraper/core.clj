(ns web-scraper.core
  (:require [clj-http.client :as client]
            [hiccup.util :as util*]
            [web-scraper.watchlist :as watchlist]
            [clojure.java.io])
  (:import (org.jsoup Jsoup)
           (java.io File))
  (:gen-class))




(defn query-resource
  [config]
  (let [url (:search-url config)
        queries (:queries config)]
      (if (= 1 (.length queries))
        (.get (Jsoup/connect (str (util*/url url {:query (first queries)})))))))
        ;Still need to figure out several queries, for now just 1
        ;(loop [[first & rest] queries
        ;       result []]
        ;  (if (empty? first)
        ;    result
        ;    (conj result (.get (Jsoup/connect (str (util*/url url {:query first}))))))
        ;  (recur rest result)))))


