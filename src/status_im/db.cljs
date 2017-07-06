(ns status-im.db
  (:require-macros [status-im.utils.db :refer [allowed-keys]])
  (:require [status-im.constants :refer [console-chat-id]]
            [status-im.utils.platform :as p]
            [cljs.spec.alpha :as s]))

;GLOBAL
(s/def ::current-public-key string?)                        ;;public key of current logged in account
(s/def ::first-run boolean?)                                ;;true when application running at first time
(s/def ::was-modal? boolean?)
(s/def ::rpc-url string?)                                   ;;"http://localhost:8545"
(s/def ::web3 any?)                                         ;;object? doesn't work
(s/def ::webview-bridge any?)                               ;;object?
(s/def ::status-module-initialized? boolean?)
(s/def ::status-node-started? (s/nilable boolean?))
(s/def ::toolbar-search map?)
(s/def ::keyboard-height number?)                           ;;height of native keyboard if shown
(s/def ::keyboard-max-height number?)
(s/def ::orientation keyword?)                              ;;:unknown - not used
(s/def ::network-status (s/nilable keyword?))               ;;:online - presence of internet connection in the phone
;ACCOUNTS
(s/def ::accounts map?)                                     ;;{id (string) account (map)} all created accounts
(s/def ::account-creation? (s/nilable boolean?))            ;;true during creating new account
(s/def ::creating-account? boolean?)                        ;;what is the difference ? ^
(s/def ::current-account-id (s/nilable string?))            ;;id of logged in account
(s/def ::recover map?)
(s/def ::login map?)                                        ;;used during logging
;NAVIGATION
(s/def ::view-id keyword?)                                  ;;current view
(s/def ::modal (s/nilable keyword?))                        ;;modal view id
(s/def ::navigation-stack seq?)                             ;;stack of view's ids (keywords)
(s/def ::prev-tab-view-id keyword?)
(s/def ::prev-view-id keyword?)
;CONTACTS
(s/def ::contacts map?)                                     ;; {id (string) contact (map)}
(s/def ::new-contacts seq?)
(s/def ::new-contact-identity string?)                      ;;public key of new contact during adding this new contact
(s/def ::new-contact-public-key-error string?)
(s/def ::contact-identity string?)                          ;;on showing this contact profile
(s/def ::contacts-ui-props map?)
(s/def ::contact-list-ui-props map?)
(s/def ::contacts-click-handler (s/nilable fn?))            ;;used in modal list (for example for wallet)
(s/def ::contacts-click-action (s/nilable keyword?))        ;;used in modal list (for example for wallet)
(s/def ::contacts-click-params map?)                        ;;used in modal list (for example for wallet)
;QR
(s/def ::qr-codes map?)                                     ;;on scan qr
(s/def ::qr-modal map?)                                     ;;used in qr modal screen
(s/def ::current-qr-context map?)
;CONTACT GROUP
(s/def ::contact-groups map?)                               ;; {id (string) group (map)}
(s/def ::contact-group-id string?)                          ;;used during editing contact group
(s/def ::group-type keyword?)                               ;;contact group or chat group
(s/def ::new-group map?)                                    ;;used during creating or edeting contact group
(s/def ::new-groups (s/nilable vector?))                    ;;used during creating or edeting contact groups
(s/def ::contacts-group (s/nilable map?))
(s/def ::selected-contacts set?)
(s/def ::groups-order seq?)                                 ;;list of group ids
;CHAT
(s/def ::chats map?)                                        ;; {id (string) chat (map)} active chats on chat's tab
(s/def ::current-chat-id string?)                           ;;current or last opened chat-id
(s/def ::chat-id string?)                                   ;;what is the difference ? ^
(s/def ::new-chat map?)                                     ;;used during adding new chat
(s/def ::new-chat-name string?)                             ;;we have name in the new-chat why do we need this field
(s/def ::chat-animations map?)                              ;;{id (string) props (map)}
(s/def ::chat-ui-props map?)                                ;;{id (string) props (map)}
(s/def ::chat-list-ui-props map?)
(s/def ::layout-height number?)                             ;;height of chat's view layout
(s/def ::expandable-view-height-to-value number?)
(s/def ::global-commands map?)                              ; {key (keyword) command (map)} atm used for browse command
(s/def ::loading-allowed boolean?)                          ;;allow to load more messages
(s/def ::message-data map?)
(s/def ::message-id->transaction-id map?)
(s/def ::message-status map?)
(s/def ::unviewed-messages (s/nilable map?))
(s/def ::selected-participants set?)
(s/def ::chat-loaded-callbacks map?)
(s/def ::commands-callbacks map?)
(s/def ::command-hash-valid? boolean?)
(s/def ::public-group-topic string?)
(s/def ::confirmation-code-sms-listener any?)               ; .addListener result object
(s/def ::messages seq?)
(s/def ::loaded-chats seq?)
(s/def ::bot-subscriptions map?)
(s/def ::new-request map?)
(s/def ::raw-unviewed-messages vector?)
;EDIT PROFILE
(s/def ::profile-edit map?)
;TRANSACTIONS
(s/def ::transactions map?)                                 ;; {id (string) transaction (map)}
(s/def ::transactions-queue map?)                           ;; {id (string) transaction (map)}
(s/def ::selected-transaction map?)
(s/def ::confirm-transactions map?)
(s/def ::confirmed-transactions-count int?)
(s/def ::transactions-list-ui-props map?)
(s/def ::transaction-details-ui-props map?)
(s/def ::wrong-password-counter int?)
(s/def ::wrong-password? boolean?)
;DISCOVERY
(s/def ::discoveries map?)                                  ;; {id (string) descovery (map)}
(s/def ::discover-search-tags seq?)
(s/def ::tags vector?)
(s/def ::current-tag map?)
(s/def ::request-discoveries-timer int?)
;NODE
(s/def ::sync-listening-started boolean?)
(s/def ::sync-state keyword?)
;NETWORK
(s/def ::network keyword?)

(s/def ::db (allowed-keys :opt-un
                          [::current-public-key
                           ::first-run
                           ::modal
                           ::was-modal?
                           ::rpc-url
                           ::web3
                           ::webview-bridge
                           ::status-module-initialized?
                           ::status-node-started?
                           ::toolbar-search
                           ::keyboard-height
                           ::keyboard-max-height
                           ::orientation
                           ::network-status
                           ::accounts
                           ::account-creation?
                           ::creating-account?
                           ::current-account-id
                           ::recover
                           ::login
                           ::view-id
                           ::navigation-stack
                           ::prev-tab-view-id
                           ::prev-view-id
                           ::contacts
                           ::new-contacts
                           ::new-contact-identity
                           ::new-contact-public-key-error
                           ::contact-identity
                           ::contacts-ui-props
                           ::contact-list-ui-props
                           ::contacts-click-handler
                           ::contacts-click-action
                           ::contacts-click-params
                           ::qr-codes
                           ::qr-modal
                           ::current-qr-context
                           ::contact-groups
                           ::contact-group-id
                           ::group-type
                           ::new-group
                           ::new-groups
                           ::contacts-group
                           ::selected-contacts
                           ::groups-order
                           ::chats
                           ::current-chat-id
                           ::chat-id
                           ::new-chat
                           ::new-chat-name
                           ::chat-animations
                           ::chat-ui-props
                           ::chat-list-ui-props
                           ::layout-height
                           ::expandable-view-height-to-value
                           ::global-commands
                           ::loading-allowed
                           ::message-data
                           ::message-id->transaction-id
                           ::message-status
                           ::unviewed-messages
                           ::selected-participants
                           ::chat-loaded-callbacks
                           ::commands-callbacks
                           ::command-hash-valid?
                           ::public-group-topic
                           ::confirmation-code-sms-listener
                           ::messages
                           ::loaded-chats
                           ::bot-subscriptions
                           ::new-request
                           ::raw-unviewed-messages
                           ::profile-edit
                           ::transactions
                           ::transactions-queue
                           ::selected-transaction
                           ::confirm-transactions
                           ::confirmed-transactions-count
                           ::transactions-list-ui-props
                           ::transaction-details-ui-props
                           ::wrong-password-counter
                           ::wrong-password?
                           ::discoveries
                           ::discover-search-tags
                           ::tags
                           ::current-tag
                           ::request-discoveries-timer
                           ::sync-listening-started
                           ::sync-state
                           ::network]))

;; initial state of app-db
(def app-db {:current-public-key         ""
             :status-module-initialized? (or p/ios? js/goog.DEBUG)
             :keyboard-height            0
             :accounts                   {}
             :navigation-stack           '()
             :contacts                   {}
             :qr-codes                   {}
             :contact-groups             {}
             :selected-contacts          #{}
             :chats                      {}
             :current-chat-id            console-chat-id
             :loading-allowed            true
             :selected-participants      #{}
             :profile-edit               {:edit?      false
                                          :name       nil
                                          :email      nil
                                          :status     nil
                                          :photo-path nil}
             :discoveries                {}
             :discover-search-tags       '()
             :tags                       []
             :sync-state                 :done
             :network                    :testnet})
