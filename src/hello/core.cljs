(ns hello.core
  (:require
    [reagent.core :as r]
    [hello.makeup-model :as m :refer [model]]
    [hello.makeup-views :as views]
    ))

(enable-console-print!)
(println "Edits to this text should show up in your developer console.")












(defn application []
  [views/application-component @model])




(r/render-component [application ]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
