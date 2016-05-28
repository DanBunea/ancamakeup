(ns hello.makeup-controller
  (:require
    [reagent.core :as r :refer [atom]]
    [hello.makeup-model :as m :refer [model]]
    ))

(defn swapm! [state model-atom]
  (swap! model-atom (fn[] state)))

;;din meniu afiseaza pagina setand :context
(defn change-context [context]
  (print "change-context" context)
  (-> @model
      (assoc :old-context (:context @model))
      (assoc :context context)
      (swapm! model)
      ))

;;afiseaza/ascunde fereastra de programari setand [:selected :service]
(defn toggle-schedule-window [service-id]
  (print "toggle-schedule-window" service-id)
  (-> @model
      (assoc-in [:selected :service] service-id)
      (swapm! model)
      ))

;;afiseaza/ascunde fereastra cu imaginea mare setand [:selected :image]
(defn toggle-image [image-id]
  (print "toggle-image" image-id)
  (-> @model
      (assoc-in [:selected :image] image-id)
      (swapm! model)
      ))

