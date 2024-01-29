// Bad practive. It should be an IAM Role.
resource "aws_iam_user" "ms_pagamento" {
  name = "ms-pagamento"
  path = "/fiap-store/"
}

resource "aws_iam_access_key" "ms_pagamento" {
  user = aws_iam_user.ms_pagamento.name
}

data "aws_iam_policy_document" "ms_pagamento" {
  statement {
    effect  = "Allow"
    actions = ["dynamodb:*"]
    resources = [
      aws_dynamodb_table.pagamentos.arn,
      "${aws_dynamodb_table.pagamentos.arn}/*"
    ]
  }
}

resource "aws_iam_user_policy" "ms_pagamento" {
  name   = "application"
  user   = aws_iam_user.ms_pagamento.name
  policy = data.aws_iam_policy_document.ms_pagamento.json
}
