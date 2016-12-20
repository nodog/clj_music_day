(ns clj-music-day.core
  (:gen-class)
  (require [clojure.java.io :as io]))


;; files are different from resources
(def directory (io/file "resources/music_day_files"))
(def files (file-seq directory))

;; resources are different from files
(def data (io/resource "music_day_files/music_day-keys.txt"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "-----------------")
  (doseq [file (rest files)] (println (slurp file)))
  (println "-----------------"))
