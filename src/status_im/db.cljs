(ns status-im.db
  (:require [status-im.constants :refer [console-chat-id]]
            [status-im.utils.platform :as p]))

;; initial state of app-db
(def app-db {;GLOBAL
             :current-public-key              "me"          ;;(string) public key of current logged in account
             :first-run                       nil           ;;(bool) true when application running at first time
             :modal                           nil           ;;(bool) true during modal screen opened
             :was-modal?                      nil           ;;(bool)
             :rpc-url                         nil           ;;(string) "http://localhost:8545"
             :web3                            nil           ;;(js object)
             :webview-bridge                  nil           ;;(js object)
             :status-module-initialized?      (or p/ios? js/goog.DEBUG) ;;(bool)
             :status-node-started?            nil           ;;(bool)
             :toolbar-search                  nil           ;;(map)

             :keyboard-height                 0             ;;(uint) height of native keyboard if shown
             :keyboard-max-height             nil           ;;(uint)
             :orientation                     nil           ;;(keyword) :unknown - not used
             :network-status                  nil           ;;(keyword) :online - presence of internet connection in the phone

             ;ACCOUNTS
             :accounts                        {}            ;;(map {id (string) account (map)}) all created accounts
             :account-creation?               nil           ;;(bool) true during creating new account
             :creating-account?               nil           ;;(bool) what is the difference ? ^
             :current-account-id              nil           ;;(string) id of logged in account
             :recover                         nil           ;;(map)

             ;LOGIN
             :login                           nil           ;;(map) used during logging

             ;NAVIGATION
             :view-id                         nil           ;;(keyword) current view
             :navigation-stack                '()           ;;(list) stack of view's ids (keywords)
             :prev-tab-view-id                nil           ;;(keyword)
             :prev-view-id                    nil           ;;(keyword)

             ;CONTACTS
             :contacts                        {}            ;;(map {id (string) contact (map)})
             :new-contacts                    nil           ;;(list)
             :new-contact-identity            nil           ;;(string) public key of new contact during adding this new contact
             :new-contact-public-key-error    nil
             :contact-identity                nil           ;;(string) show profile
             :contacts-ui-props               nil           ;;(map)
             :contact-list-ui-props           nil           ;;(map)
             :contacts-click-handler          nil           ;;(function) used in modal list (for example for wallet)
             :contacts-click-action           nil           ;;(?)
             :contacts-click-params           nil           ;;(map)

             ;QR
             :qr-codes                        {}            ;;(map) on scan qr
             :qr-modal                        nil           ;;(map) used in qr modal screen
             :current-qr-context              nil

             ;CONTACT GROUP
             :contact-groups                  {}            ;;(map {id (string) group (map)})
             :contact-group-id                nil           ;;(string) using during editing contact group
             :group-type                      nil           ;;(keyword) contact or chat group
             :new-group                       nil           ;;(map) used during creating or edeting contact group
             :new-groups                      nil           ;;(seq) used during creating or edeting contact groups
             :contacts-group                  nil
             :selected-contacts               #{}
             :groups-order                    nil           ;;(list)  of group ids

             ;CHAT
             :chats                           {}            ;;(map {id (string) chat (map)}) active chats on chat's tab
             :current-chat-id                 console-chat-id ;;(string) current or last opened chat-id
             :chat-id                         nil           ;;(string) what is the difference ? ^
             :new-chat                        nil           ;;(map) used during adding new chat
             :new-chat-name                   nil           ;;(string) we have name in the new-chat why do we need this field
             :chat-animations                 nil           ;;(map {id (string) props (map)})
             :chat-ui-props                   nil           ;;(map {id (string) props (map)})
             :layout-height                   nil           ;;(uint) height of chat's view layout
             :expandable-view-height-to-value nil           ;; (uint)
             :global-commands                 nil           ;;(map {key (keyword) command (map)}) atm used for browse command
             :loading-allowed                 true          ;;(bool) allow load more messages
             :message-data                    nil           ;;(map)
             :message-id->transaction-id      nil           ;;(map)
             :message-status                  nil           ;;(map)
             :unviewed-messages               nil           ;;(map)
             :selected-participants           #{}
             :chat-loaded-callbacks           nil           ;;(map)
             :commands-callbacks              nil           ;;(map)
             :command-hash-valid?             nil           ;;(bool)
             :public-group/topic              nil           ;;(string?)
             :confirmation-code-sms-listener  nil
             :canceled-command                nil
             :messages                        nil
             :loaded-chats                    nil
             :bot-subscriptions               nil           ;;(map)
             :new-request                     nil           ;;(map)
             :raw-unviewed-messages           nil

             ;EDIT PROFILE
             :profile-edit                    {:edit?      false ;;(map) used during editing profile
                                               :name       nil
                                               :email      nil
                                               :status     nil
                                               :photo-path nil}

             ;TRANSACTIONS
             :transactions                    nil           ;;(map {id (string) transaction (map)})
             :transactions-queue              nil           ;;(map {id (string) transaction (map)})
             :selected-transaction            nil           ;;(?) ?
             :confirm-transactions            nil           ;;(map)
             :confirmed-transactions-count    nil           ;;(uint?) ?
             :transactions-list-ui-props      nil           ;;(map)
             :transaction-details-ui-props    nil           ;;(map)
             :wrong-password-counter          nil           ;;(uint)
             :wrong-password?                 nil           ;;(bool)

             ;DISCOVERY
             :discoveries                     {}            ;;(map {id (string) descovery (map)})
             :discover-search-tags            []            ;;(vector)
             :tags                            []            ;;(vector)
             :current-tag                     nil           ;;(map?)
             :request-discoveries-timer       nil           ;;(uint) setInterval indentity

             ;NODE SYNC
             :sync-listening-started          nil           ;;(bool)
             :sync-state                      :done         ;;(keyword)

             ;NETWORK
             :network                         :testnet})
