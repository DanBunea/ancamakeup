(ns hello.makeup-views
  (:require
    [reagent.core :as r :refer [atom]]
    [hello.makeup-model :as m :refer [model]]
    [hello.makeup-controller :as controller]
    ))


(defn header-component [state]
  [:div#header

   [:h1.center {
                 :style {
                          :font-size "80px"
                          :color "#DAA520"

                          }

                 }
    "Makeup by Anca Bunea"]


   [:a
    {
      :class "menu_option"
         :on-click #(controller/change-context "home")
         }
    "Home"
    ]
   [:a
    {
      :class "menu_option"
         :on-click #(controller/change-context "services")
         }
    "Services"
    ]

   [:a
    {
      :class "menu_option"
         :on-click #(controller/change-context "gallery")
         }
    "Gallery"
    ]

   [:a
    {
      :class "menu_option"
         :on-click #(controller/change-context "contact")
         }
    "Contact"
    ]



   ])

(defn programare-modal-component [state]
  [:div#programare-modal-component.overlay
   {
    :on-click #(controller/toggle-schedule-window nil)
    }
   [:div.modal
   [:h1 "Programare Modal component"]]])

(defn home-component [state]
  [:div#home
   [:h1 "Home"]
   [:h2
    {
      :id "hello"
      :style {
               :color "red"
               }
      }
    (:text state)]
   [:div#main
    [:div#left_gallery.main_column
     [:img {
              :src "/images/IMG_1.jpg"
              :style {
                       :width "850px"
                       :height "650px"
                       }
              }]]
    [:div#categories.main_column
     (let [services-on-first-page (->> (vals (:services state))
                                       (filter #(not= nil (:show-on-homepage-position %)))
                                       (sort-by :show-on-homepage-position < )
                                       )]
       (for [service services-on-first-page]
         ^{:key  (:name service)}
         [:div#category_event.category
          [:figure
          [:img {
                 :src (:image service)

                 }]]
          [:span.category_title (:name service)]
          [:a.more
           {
            :on-click #(controller/toggle-schedule-window (:id service))
            }
           "...mai mult"]
          ]))
     ]]
   (when (not= nil (get-in state [:selected :service]))
     [programare-modal-component state])
   ])

(defn gallery-modal-component [state]
  [:div
   {
    :id "gallery-modal-component"
    :class "overlay"
    :on-click #(controller/toggle-image nil)
    }
   [:div.modal
    (let [path [:images (get-in state [:selected :image])]
          image (get-in state path)]
      [:img {
             :src (:url image)
             :style {:width 600}
             }])
    ]
   ])


(defn gallery-component [state]
  [:div#gallery
   [:h1 "Gallery"]
   (for [image (vals (:images state))]
     ^{:key (:id image)}
     [:figure
      [:img
       {
        :src (:url image)
        :style {:width 300}
        :on-click #(controller/toggle-image (:id image))
        }]])

   (when (not= nil (get-in state [:selected :image]))
     [gallery-modal-component state])
   ])

(defn services-component [state]
  [:div#services
   [:h1 "Services"]

   (for [service (vals (:services state))]
     ^{:key  (:name service)}
     [:div#category_event.category
        {
            :on-click #(controller/toggle-schedule-window (:id service))
            }


      [:figure
       [:img {
              :src (:image service)

              }]]
      [:span.category_title (:name service)]
      [:span.category_description (:description service)]
      [:a.more "programare"]
      ])
    (when (not= nil (get-in state [:selected :service]))
     [programare-modal-component state])
   ])

(defn contact-component [state]
  [:div#contact
   [:h1 "Contact"]])






(defn application-component [state]
  [:div#app
  [header-component state]
  (cond
    (= (:context state)"home") [home-component state]
    (= (:context state) "gallery") [gallery-component state]
    (= (:context state) "services") [services-component state]
    (= (:context state) "contact") [contact-component state]


    )
   ])
