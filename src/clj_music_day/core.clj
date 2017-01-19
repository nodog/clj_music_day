(ns clj-music-day.core
  (:gen-class)
  (require [clojure.java.io :as io])
  (require [clj-time.core :as t])
  (require [clj-time.format :as f])
  (require [clj-time.local :as l]))


;; files are different from resources
(def directory (io/file "resources/music_day_files"))
(def files (file-seq directory))

;; resources are different from files
(def data (io/resource "music_day_files/music_day-keys.txt"))
(def schedule-data (io/resource "music_day_files/music_day-schedule.txt"))

(defn randomish
  ([seed]
    (repeatedly
      (let [gen (java.util.Random. seed)]
        (fn [] (.nextInt gen)))))
  ([seed limit]
    (repeatedly
      (let [gen (java.util.Random. seed)]
        (fn [] (.nextInt gen limit))))))

(defn seed-from-day-of-year
  "return a good random seed from the year and day of year"
  []
  (int (read-string (f/unparse custom-formatter (l/local-now)))))

(defn schedule
  "Return the schedule of the practice."
  []
  (slurp schedule-data))

(defn day-practice
  "Return the day's practice."
  []
  (str "day practice\n"))

(defn key-practice
  "Return the key's practice."
  []
  (str "key practice\n"))

(defn chord-method-practice
  "Return the chord-method-practice."
  []
  (str "chord-method-practice practice\n"))

(defn hand-independence-practice
  "Return the hand-independence-practice."
  []
  (str "hand-independence-practice.\n"))

(def custom-formatter (f/formatter "yyyyDDD"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [a 5]
    (println "-----------------")
    (println (str (schedule)
                  (day-practice)
                  (key-practice)
                  (chord-method-practice)
                  (hand-independence-practice)
                  (f/unparse custom-formatter (l/local-now)) "\n"
                  (seed-from-day-of-year) "\n"
                  (second (randomish (seed-from-day-of-year) 1024))))

    ;(doseq [file (rest files)] (println (slurp file)))
    ;std::string MusicDay::describe() {
    ;std::locale::global(std::locale("en_US.UTF-8"));
    ;std::time_t t = std::time(nullptr);
    ;std::tm *ltm = localtime(&t);
    ;auto year = 1900 + ltm->tm_year;
    ;auto yday = ltm->tm_yday;
    ;auto seed = year*1000 + yday + 1;
    ;std::mt19937 gen;
    ;gen.seed(seed);
    ;rand_generator = gen;

    ;std::string describe_practice;
    ;describe_practice += schedule();
    ;describe_practice += day_practice(ltm, seed);
    ;describe_practice += key_practice();
    ;describe_practice += chord_method_practice();
    ;describe_practice += hand_independence_practice();
    ;return describe_practice;

    (println "-----------------")))
