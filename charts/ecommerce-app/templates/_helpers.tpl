{{- define "ecommerce-app.name" -}}
{{- .Chart.Name -}}
{{- end -}}

{{- define "ecommerce-app.fullname" -}}
{{- .Release.Name -}}
{{- end -}}

{{- define "ecommerce-app.labels" -}}
app.kubernetes.io/name: {{ include "ecommerce-app.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
helm.sh/chart: {{ .Chart.Name }}-{{ .Chart.Version | replace "+" "_" }}
{{- end -}}
