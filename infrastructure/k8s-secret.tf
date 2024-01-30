resource "kubernetes_secret" "example" {
  metadata {
    name      = "aws-credentials"
    namespace = "ms-pagamento"
  }

  data = {
    aws_access_key = aws_iam_access_key.ms_pagamento.id     # Bad practice. It should use IAM Role and OIDC.
    aws_secret_key = aws_iam_access_key.ms_pagamento.secret # Bad practice. It should use IAM Role and OIDC.
  }

  type = "opaque"

  depends_on = [kubernetes_namespace.ms_pagamento]
}
