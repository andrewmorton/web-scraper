(ns web-scraper.watchlist
  (:gen-class))

;;====================================
;;======= Watchlists =================
;;====================================
(def first-watchlist (atom {:craigslist {:search-url "https://ithaca.craigslist.org/search/sss"
                                         :queries ["analog rack"]
                                         :filters []}}))

;;====================================
;;======= End Watchlists =============
;;====================================



(defn get-site
  "Gets a sequence of all site keywords from @watchlist. returns a seq of site names."
  [watchlist]
  (let [watchlist @watchlist]
    (loop [[head & rest] watchlist
           results (seq [])]
        (if-not (empty? head)
          (do (into results [(first head)]) (recur rest results))
          results))))