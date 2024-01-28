resource "kubernetes_namespace" "ms_pagamento" {
  metadata {
    name = "ms-pagamento"
  }
}
