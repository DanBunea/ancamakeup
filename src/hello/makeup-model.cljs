(ns hello.makeup-model
  (:require [reagent.core :as r :refer [atom]]))

;; define your app data so that it doesn't get over-written on reload
(defonce model
  (r/atom
   {
    :context "home"
    :selected {
               :image nil
               :service nil
               }

    :text "Hello world, Ancuta!"
    :services
    {
     :event {
             :id :event
             :name "Machiaj veniment"
             :description ""
             :image "/images/eveniment.jpg"
             :price 120
             :show-on-homepage-position 1
             }
     :bride {
             :id :bride
             :name "Machiaj mireasa"
             :description ""
             :image "/images/mirese.jpg"
             :price 120
             :show-on-homepage-position 2
             }
     :photo {
             :id :photo
             :name "Machiaj sesiune "
             :description ""
             :price 120
             :show-on-homepage-position nil
             }
     }
    :images
    {
     :IMG_1 {
             :id :IMG_1
             :url "/images/IMG_1.jpg"
             :show-on-homepage-position 1
             }
     :IMG_2 {
             :id :IMG_2
             :url "/images/IMG_2.jpg"
             :show-on-homepage-position 2
             }
     :IMG_3 {
             :id :IMG_3
             :url "/images/IMG_3.jpg"
             :show-on-homepage-position nil
             }
     }
    }
   ))


(defn print-model []
  (clj->js @model))


